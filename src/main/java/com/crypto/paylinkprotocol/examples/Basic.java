package com.crypto.paylinkprotocol.examples;

import com.crypto.paylinkprotocol.manager.PayLinkProtocolManager;

import io.reactivex.disposables.Disposable;

public class Basic {

    public static void main(String[] args) {
        PayLinkProtocolManager payLinkProtocolManager = new PayLinkProtocolManager(
                "",
                0);
        Disposable disposable = payLinkProtocolManager.subscribe().subscribe(System.out::println, Throwable::printStackTrace);
    }

}