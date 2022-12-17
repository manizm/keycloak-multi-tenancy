package dev.sultanov.keycloak.multitenancy.models.jpa;

import dev.sultanov.keycloak.multitenancy.models.Tenant;
import dev.sultanov.keycloak.multitenancy.models.TenantInvitation;
import dev.sultanov.keycloak.multitenancy.models.jpa.entity.TenantInvitationEntity;
import java.util.Set;
import javax.persistence.EntityManager;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.jpa.JpaModel;

public class TenantInvitationAdapter implements TenantInvitation, JpaModel<TenantInvitationEntity> {

    private final KeycloakSession session;
    private final TenantInvitationEntity invitation;
    private final EntityManager em;
    private final RealmModel realm;

    public TenantInvitationAdapter(KeycloakSession session, RealmModel realm, EntityManager em, TenantInvitationEntity invitation) {
        this.session = session;
        this.em = em;
        this.invitation = invitation;
        this.realm = realm;
    }


    @Override
    public String getId() {
        return invitation.getId();
    }

    @Override
    public Tenant getTenant() {
        return session.getProvider(TenantProvider.class).getById(realm, invitation.getTenant().getId()).orElseThrow();
    }

    @Override
    public String getEmail() {
        return invitation.getEmail();
    }


    @Override
    public Set<String> getRoles() {
        return invitation.getRoles();
    }


    @Override
    public UserModel getInvitedBy() {
        return session.users().getUserById(realm, invitation.getInvitedBy());
    }

    @Override
    public TenantInvitationEntity getEntity() {
        return invitation;
    }
}
