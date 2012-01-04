
package org.mule.module.dynamicFlows.config;

import org.mule.api.Capabilities;
import org.mule.api.Capability;


/**
 * A <code>DynamicFlowsModuleCapabilitiesAdapter</code> is a wrapper around {@link org.mule.module.dynamicFlows.DynamicFlowsModule } that implements {@link org.mule.api.Capabilities} interface.
 * 
 */
public class DynamicFlowsModuleCapabilitiesAdapter
    extends org.mule.module.dynamicFlows.DynamicFlowsModule
    implements Capabilities
{


    /**
     * Returns true if this module implements such capability
     * 
     */
    public boolean isCapableOf(Capability capability) {
        if (capability == Capability.LIFECYCLE_CAPABLE) {
            return true;
        }
        return false;
    }

}
