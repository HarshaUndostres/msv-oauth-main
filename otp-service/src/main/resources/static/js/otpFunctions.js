var agconnect = require("@agconnect/api");
require('@agconnect/instance');
require('@agconnect/auth');
require('@agconnect/network');

const VERBOSE = 0;
function log(msg, level=1) {
    if(VERBOSE >= level)
        console.log(msg);
}

log(agconnect, 3);

// AGConnect helper functions
function initConfig(config) {
    agconnect.instance().configInstance(config);
}
function setUserInfoPersistence(saveMode) {
    agconnect.auth().setUserInfoPersistence(saveMode);
}
function getVerifyCode(countryCode, account, lang, sendInterval) {
    return agconnect.auth.PhoneAuthProvider.requestVerifyCode(
        countryCode,
        account,
        agconnect.auth.Action.ACTION_REGISTER_LOGIN,
        lang,
        sendInterval,
    )
        .then((ret) => {
            return Promise.resolve(ret);
        })
        .catch((err) => {
            return Promise.reject(err);
        });
}
function getPhoneCredential(countryCode, account, password, verifyCode) {
    if (verifyCode) {
        return agconnect.auth.PhoneAuthProvider.credentialWithVerifyCode(countryCode, account, password, verifyCode);
    }
    return agconnect.auth.PhoneAuthProvider.credentialWithPassword(countryCode, account, password);
}
function login(credential) {
    return agconnect
        .auth()
        .signIn(credential)
        .then((res) => {
            return Promise.resolve(res);
        })
        .catch((err) => {
            return Promise.reject(err);
        });
}
function loginWithPhone(countryCode, account, password, verifyCode) {
    let credential = getPhoneCredential(countryCode, account, password, verifyCode);
    if (!credential) {
        return Promise.reject('credential is undefined');
    }
    return login(credential);
}

let agConnectConfig = {
    "agcgw":{
        "backurl":"connect-dra.dbankcloud.cn",
        "url":"connect-dra.hispace.hicloud.com"
    },
    "client":{
        "cp_id":"890052000000000872",
        "product_id":"9105385871708111093",
        "client_id":"194325309598729408",
        "client_secret":"493A9767115C1ECA5F145DD1807762658ED45EBD6C0803A54C21EC8C39034BB7",
        "app_id":"101162505",
        "package_name":"www.undostres.com.mx",
        "api_key":"CV5ZWDfKFzrQMCnKzLp3pOg0kLG+Dlkefh0gfI41VVkNWygiq9jLKYtGD1DmuG/SYAQAp8vZpWIvxAtUOvEMqnhMv+TK"
    },
    "service":{
        "analytics":{
            "collector_url":"datacollector-dra.dt.hicloud.com,datacollector-dra.dt.dbankcloud.cn",
            "resource_id":"p1",
            "channel_id":""
        },
        "search":{
            "url":"https://search-dra.cloud.huawei.com"
        },
        "cloudstorage":{
            "storage_url":"https://ops-dra.agcstorage.link"
        },
        "ml":{
            "mlservice_url":"ml-api-dra.ai.dbankcloud.com,ml-api-dra.ai.dbankcloud.cn"
        }
    },
    "region":"SG",
    "configuration_version":"1.0"
};

function sendOtp(countryCode, mobile){
    log('Initializing agconnect...');
    initConfig(agConnectConfig);
    setUserInfoPersistence(2);
    log('Request mobile otp...');
    getVerifyCode(countryCode, mobile, 'es-419', 90, 90)
        .then((ret) => {
            console.log('OTP Send OK');
        })
        .catch((err) => {
            console.log(JSON.stringify(err));
        });
}

function verifyOtp(countryCode, mobile, verificationCode){
    log('Initializing agconnect...');
    initConfig(agConnectConfig);
    setUserInfoPersistence(2);
    log('Request mobile otp...');
    loginWithPhone(
        countryCode,
        mobile,
        '',
        verificationCode,
    )
        .then((res) => {
            console.log('OTP Verify OK');
        })
        .catch((err) => {
            console.log(JSON.stringify(err));
        });
}

module.exports = {
    sendOtp,
    verifyOtp,
    log
};
