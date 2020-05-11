Instagram Authenticator Plug-in
===============================
   
.. image:: https://curity.io/assets/images/badges/instagram-authenticator-quality.svg
       :target: https://curity.io/resources/code-examples/status/
       
.. image:: https://curity.io/assets/images/badges/instagram-authenticator-availability.svg
       :target: https://curity.io/resources/code-examples/status/

This project provides an opens source Instagram Authenticator plug-in for the Curity Identity Server. This allows an administrator to add functionality to Curity which will then enable end users to login using their Instagram credentials. The app that integrates with Curity may also be configured to receive the Instagram access token and refresh token, allowing it to manage resources in Instagram.

System Requirements
~~~~~~~~~~~~~~~~~~~

* Curity Identity Server 2.4.0 and `its system requirements <https://developer.curity.io/docs/latest/system-admin-guide/system-requirements.html>`_

Requirements for Building from Source
"""""""""""""""""""""""""""""""""""""

* Maven 3
* Java JDK v. 8

Compiling the Plug-in from Source
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

The source is very easy to compile. To do so from a shell, issue this command: ``mvn package``.

Installation
~~~~~~~~~~~~

To install this plug-in, either download a binary version available from the `releases section of this project's GitHub repository <https://github.com/curityio/instagram-authenticator/releases>`_ or compile it from source (as described above). If you compiled the plug-in from source, the package will be placed in the ``target`` subdirectory. The resulting JAR file or the one downloaded from GitHub needs to placed in the directory ``${IDSVR_HOME}/usr/share/plugins/instagram``. (The name of the last directory, ``instagram``, which is the plug-in group, is arbitrary and can be anything.) After doing so, the plug-in will become available as soon as the node is restarted.

.. note::

    The JAR file needs to be deployed to each run-time node and the admin node. For simple test deployments where the admin node is a run-time node, the JAR file only needs to be copied to one location.

For a more detailed explanation of installing plug-ins, refer to the `Curity developer guide <https://developer.curity.io/docs/latest/developer-guide/plugins/index.html#plugin-installation>`_.

Creating an App in Instagram
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

As `described in the Instagram documentation <https://www.instagram.com/developer>`_, you can `create <https://www.instagram.com/developer/clients/register>`_ apps that use the Instagram APIs as shown in the following figure:


.. figure:: docs/images/new-instagram-app1.png
    :name: new-instagram-app
    :align: center
    :width: 500px

.. figure:: docs/images/new-instagram-app2.png
    :name: new-instagram-app
    :align: center
    :width: 500px

Then, give the app a name, e.g., ``Curity-Integration-App``.

When you view the app's configuration after creating it, you'll find the ``Client ID`` and ``Client Secret``. These will be needed later when configuring the plug-in in Curity.

Instagram will also display the Redirect URI's in the new app's configuration. This needs to match the yet-to-be-created Instagram authenticator instance in Curity. The default will not work, and, if used, will result in an error. This should be updated to some URL that follows the pattern ``$baseUrl/$authenticationEndpointPath/$instagramAuthnticatorId/callback``, where each of these URI components has the following meaning:

============================== =========================================================================================
URI Component                  Meaning
------------------------------ -----------------------------------------------------------------------------------------
``baseUrl``                    The base URL of the server (defined on the ``System --> General`` page of the
                               admin GUI). If this value is not set, then the server scheme, name, and port should be
                               used (e.g., ``https://localhost:8443``).
``authenticationEndpointPath`` The path of the authentication endpoint. In the admin GUI, this is located in the
                               authentication profile's ``Endpoints`` tab for the endpoint that has the type
                               ``auth-authentication``.
``instagramAuthenticatorId``   This is the name given to the Instagram authenticator when defining it (e.g., ``instagram1``).
============================== =========================================================================================

Creating a Instagram Authenticator in Curity
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

The easiest way to configure a new Instagram authenticator is using the Curity admin UI. The configuration for this can be downloaded as XML or CLI commands later, so only the steps to do this in the GUI will be described.

1. Go to the ``Authenticators`` page of the authentication profile wherein the authenticator instance should be created.
2. Click the ``New Authenticator`` button.
3. Enter a name (e.g., ``instagram1``). This name needs to match the URI component in the callback URI set in the Instagram app.
4. For the type, pick the ``Instagram`` option:

    .. figure:: docs/images/instagram-authenticator-type-in-curity.png
        :align: center
        :width: 600px

5. On the next page, you can define all of the standard authenticator configuration options like any previous authenticator that should run, the resulting ACR, transformers that should executed, etc. At the bottom of the configuration page, the Instagram-specific options can be found.

    .. note::

        The Instagram-specific configuration is generated dynamically based on the `configuration model defined in the Java interface <https://github.com/curityio/instagram-authenticator/blob/master/src/main/java/io/curity/identityserver/plugin/instagram/config/InstagramAuthenticatorPluginConfig.java>`_.

6. Certain required and optional configuration settings may be provided. One of these is the ``HTTP Client`` setting. This is the HTTP client that will be used to communicate with the Instagram OAuth server's token and user info endpoints. To define this, do the following:

    A. click the ``Facilities`` button at the top-right of the screen.
    B. Next to ``HTTP``, click ``New``.
    C. Enter some name (e.g., ``instagramClient``).

        .. figure:: docs/images/instagram-http-client.png
            :align: center
            :width: 400px

7. Back in the Instagram authenticator instance that you started to define, select the new HTTP client from the dropdown.

    .. figure:: docs/images/http-client.png


8. In the ``Client ID`` textfield, enter the client id from the Instagram app.
9. Also enter the matching ``Client Secret``.
10. If you wish to limit the scopes that Curity will request of Instagram, toggle on the desired scopes (e.g., ``Public Content`` or ``Follower List``).

Once all of these changes are made, they will be staged, but not committed (i.e., not running). To make them active, click the ``Commit`` menu option in the ``Changes`` menu. Optionally enter a comment in the ``Deploy Changes`` dialogue and click ``OK``.

Once the configuration is committed and running, the authenticator can be used like any other.

License
~~~~~~~

This plugin and its associated documentation is listed under the `Apache 2 license <LICENSE>`_.

More Information
~~~~~~~~~~~~~~~~

Please visit `curity.io <https://curity.io/>`_ for more information about the Curity Identity Server.

Copyright (C) 2017 Curity AB.
