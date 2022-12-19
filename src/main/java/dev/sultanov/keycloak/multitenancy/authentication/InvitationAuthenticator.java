package dev.sultanov.keycloak.multitenancy.authentication;

import dev.sultanov.keycloak.multitenancy.model.TenantProvider;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

public class InvitationAuthenticator implements Authenticator {

    public InvitationAuthenticator() {
    }

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        context.success();
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        context.attempted();
    }

    @Override
    public boolean requiresUser() {
        return true;
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        TenantProvider tenantProvider = session.getProvider(TenantProvider.class);
        return tenantProvider.getTenantInvitationsStream(realm, user).findAny().isEmpty();
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
        user.addRequiredAction(InvitationRequiredActionFactory.ID);
    }

    @Override
    public void close() {
    }
}
