Dynamic Flows Module
====================

Dynamic Flows Module allows you to add, remove and run mule context into your mule application without restarting the
server.

Adding new contexts:
===================

The add Message Processor allows you to create a new mule context in your mule application by just sending the context name
and a list of configuration XMLs.


        <flow name="addFlow">
        <http:inbound-endpoint keep-alive="true" exchange-pattern="one-way" host="localhost" port="10443" path="add"/>

        <transformer ref="mapForDynamicFlowsModuleTransformer"/>

        <dynamicflows:add contextName="#[map-payload:contextName]">
           <dynamicflows:configs ref="#[map-payload:configs]" />
        </dynamicflows:add>
        </flow>


Removing contexts
=================

 Removes a context added by Add.

       <flow name="testDelete">
        <dynamicflows:remove contextName="testContext"/\>
        </flow>


Run flow
========

Executes a flow with VM inbound.

        <flow name="runFlow">
                <http:inbound-endpoint keep-alive="true" exchange-pattern="request-response" host="localhost" port="10443" path="run"/>
                <transformer ref="httpToMapTransformer"/>

                <dynamicflows:vm-run contextName="#[map-payload:contextName]" flowName="#[map-payload:flowName]"/>

        </flow>

Executes a flow without VM inbound.

        <flow name="runFlow">
                <http:inbound-endpoint keep-alive="true" exchange-pattern="request-response" host="localhost" port="10443" path="run"/>
                <transformer ref="httpToMapTransformer"/>

                <dynamicflows:run contextName="#[map-payload:contextName]" flowName="#[map-payload:flowName]"/>

        </flow>