require('dotenv').config()

const SunshineConversationsClient = require('sunshine-conversations-client');
const defaultClient = SunshineConversationsClient.ApiClient.instance;

/*
    For simplicity in this example we are using basic auth. You should fill in these
    values in the .env file.
 */
const basicAuth = defaultClient.authentications['basicAuth'];
basicAuth.username = process.env.SMOOCH_KEY_ID;
basicAuth.password = process.env.SMOOCH_KEY_SECRET;

// Send a simple text message from the bot
exports.sendMessage = function (message, conversationId) {
    const messageJson = { author: { "type": "business" }, content: { type: "text", text: message } };
    const messagesApiInstance = new SunshineConversationsClient.MessagesApi();
    messagesApiInstance.postMessage(process.env.APP_ID, conversationId, messageJson).then(function(data) {
        console.log('Message sent successfully');
    }, function(error) {
        console.error(error);
    });
}

exports.getIntegrations = function () {
    const apiInstance = new SunshineConversationsClient.IntegrationsApi();
    const opts = {
        'page': new SunshineConversationsClient.Page(), // Page | Contains parameters for applying cursor pagination.
        'filter': new SunshineConversationsClient.IntegrationListFilter() // IntegrationListFilter | Contains parameters for filtering the results.
    };
    return apiInstance.listIntegrations(process.env.APP_ID, opts);
}

exports.createSwitchboard = (appId) => {
    const apiInstance = new SunshineConversationsClient.SwitchboardsApi();
    return apiInstance.createSwitchboard(appId)
};

exports.updateSwitchboard = (appId, switchboardId, switchboardUpdateBody) => {
    const apiInstance = new SunshineConversationsClient.SwitchboardsApi();
    return apiInstance.updateSwitchboard(appId, switchboardId, switchboardUpdateBody)
}

exports.createSwitchboardIntegrationWithId = (appId, switchboardId, integrationName, integrationId, nextSwitchboardIntegrationId) => {
    const apiInstance = new SunshineConversationsClient.SwitchboardIntegrationsApi();
    const switchboardIntegrationCreateBody = {
        "name": integrationName,
        "integrationId": integrationId,
        "nextSwitchboardIntegrationId": nextSwitchboardIntegrationId
    };
    return apiInstance.createSwitchboardIntegration(appId, switchboardId, switchboardIntegrationCreateBody)
}

exports.createSwitchboardIntegrationWithType = (appId, switchboardId, integrationName, integrationtype, nextSwitchboardIntegrationId) => {
    const apiInstance = new SunshineConversationsClient.SwitchboardIntegrationsApi();
    const switchboardIntegrationCreateBody = {
        "name": integrationName,
        "integrationType": integrationtype,
        "nextSwitchboardIntegrationId": nextSwitchboardIntegrationId,
        "deliverStandbyEvents": false,
        "messageHistoryCount": 10
    };
    return apiInstance.createSwitchboardIntegration(appId, switchboardId, switchboardIntegrationCreateBody)
}

exports.updateSwitchboardIntegration = (appId, switchboardId, switchboardIntegrationId,switchboardIntegrationUpdateBody) => {
    const apiInstance = new SunshineConversationsClient.SwitchboardIntegrationsApi();
    return apiInstance.updateSwitchboardIntegration(appId, switchboardId, switchboardIntegrationId, switchboardIntegrationUpdateBody)
}

exports.listSwitchboardIntegrations = (appId, switchboardId) => {
    const apiInstance = new SunshineConversationsClient.SwitchboardIntegrationsApi();
    return apiInstance.listSwitchboardIntegrations(appId, switchboardId)
}

exports.listSwitchboards = (appId) => {
    const apiInstance = new SunshineConversationsClient.SwitchboardsApi();
    return apiInstance.listSwitchboards(appId)
};

exports.deleteSwitchboard = (appId, switchboardId) => {
    var apiInstance = new SunshineConversationsClient.SwitchboardsApi();
    apiInstance.deleteSwitchboard(appId, switchboardId)
}

exports.deleteAnyExistingSwitchboardsForAppId = async (appId) => {
    const apiInstance = new SunshineConversationsClient.SwitchboardsApi();
    let response = await apiInstance.listSwitchboards(appId);
    console.log(JSON.stringify(response, null, 2));
    const switchboards = response.switchboards;
    for (const switchboard of switchboards) {
        let deleteResponse = await apiInstance.deleteSwitchboard(appId, switchboard.id)
        console.log(JSON.stringify(deleteResponse, null, 2));
    }
}
