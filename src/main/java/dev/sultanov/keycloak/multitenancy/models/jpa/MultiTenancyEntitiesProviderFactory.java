package dev.sultanov.keycloak.multitenancy.models.jpa;

import org.keycloak.Config.Scope;
import org.keycloak.connections.jpa.entityprovider.JpaEntityProvider;
import org.keycloak.connections.jpa.entityprovider.JpaEntityProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class MultiTenancyEntitiesProviderFactory implements JpaEntityProviderFactory {

    protected static final String ID = "multi-tenancy-entities-provider";

    @Override
    public JpaEntityProvider create(KeycloakSession session) {
        return new MultiTenancyEntitiesProvider();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void init(Scope config) {
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
    }

    @Override
    public void close() {
    }
}
