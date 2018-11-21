package server;

import java.security.Permission;

public class customSecurityManager extends SecurityManager {

    SecurityManager original;

    customSecurityManager(SecurityManager original) {
        this.original = original;
    }

    /**
     * Deny permission to exit the VM.
     */
    @Override
	public void checkExit(int status) {
        //throw(new SecurityException("Not allowed"));
    }

    /**
     * Allow this security manager to be replaced, if fact, allow pretty
     * much everything.
     */
    @Override
	public void checkPermission(Permission perm) {
    }

    public SecurityManager getOriginalSecurityManager() {
        return original;
    }
}