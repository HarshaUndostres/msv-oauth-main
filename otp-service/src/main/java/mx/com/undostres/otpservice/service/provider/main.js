const otp = require("./otpFunctions");

otp.log(process.argv.length);

function otpfunction (countryCode,mobile,operation){

    otp.log(`Country code: ${countryCode}, Mobile: ${mobile}, Operation: ${operation}`);

    if (operation === 'send') {
        otp.sendOtp(countryCode, mobile);
    } else if(operation === 'verify' && process.argv.length > 5) {
        let verificationCode = process.argv[5];
        otp.verifyOtp(countryCode, mobile, verificationCode);
    } else {
        console.log("Error: Invalid operation");
    }
}