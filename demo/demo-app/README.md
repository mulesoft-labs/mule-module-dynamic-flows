ABOUT DEMO APP
==============

This demo shows how dynamic flows can be used. It modifies a custom filed called 'packed' in the user campaigns. Users
can define a different file packing for each campaign using dynamic flows.

To use this application:
=======================

1) Set your salesforce credentials in the mule config
2) Deploy the demo-app in mule
3) Open the src/main/app/config-creator.html to create mappings and register new context into mule.
4) Execute the new context you added by typing http://localhost:{http.port}/run?campaignName=<your campaign>

