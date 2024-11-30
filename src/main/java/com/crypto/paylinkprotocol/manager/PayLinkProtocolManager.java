package com.crypto.paylinkprotocol.manager;

import com.crypto.paylinkprotocol.model.Event;
import com.crypto.paylinkprotocol.resources.Finals;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

import io.reactivex.Flowable;

public class PayLinkProtocolManager {

    // Dependencies
    private final long appId;

    // Runtime
    private final Web3j web3j;

    public PayLinkProtocolManager(String rpcUrl, long appId) {
        this.appId = appId;
        this.web3j = Web3j.build(new HttpService(rpcUrl));
    }

    public PayLinkProtocolManager(long appId) {
        this("https://ethereum-rpc.publicnode.com", appId);
    }

    public Flowable<Event> subscribe() {
        final EthFilter filter = new EthFilter(DefaultBlockParameterName.SAFE, DefaultBlockParameterName.LATEST, Finals.PLP_ROUTER_ADDRESS);
        filter.addSingleTopic(Finals.PLP_PURCHASE_TOPIC);
        filter.addSingleTopic(Numeric.toHexStringWithPrefixZeroPadded(BigInteger.valueOf(appId), 64));
        return web3j.ethLogFlowable(filter).switchMap(log -> {
            try {
                final String logData = Numeric.cleanHexPrefix(log.getData());
                final String purchasedToken = Numeric.toHexStringWithPrefix(Numeric.toBigInt(log.getTopics().get(2)));
                final long userId = Numeric.toBigInt(logData.substring(0, 64)).longValue();
                final BigInteger purchaseAmount = Numeric.toBigInt(logData.substring(64, (64 * 2)));
                final String customerUserAddress = Numeric.toHexStringWithPrefix(Numeric.toBigInt(logData.substring((64 * 2), (64 * 3))));
                return Flowable.just(new Event(appId, purchasedToken, purchaseAmount, userId, customerUserAddress));
            } catch (Exception ex) {
                return Flowable.error(ex);
            }
        });
    }

}