package tn.esprit.pitwin.services;

import io.jsonwebtoken.impl.crypto.MacProvider;
import tn.esprit.pitwin.interfaces.ITokenService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.security.Key;

@Singleton
@Startup
public class TokenService implements ITokenService {
	
    private Key key;

    @PostConstruct
    public void initKey() {
        key = MacProvider.generateKey();
        System.out.println("Key generated ... " + key.getEncoded().length);
    }

    public Key getKey() {
        return key;
    }
}

