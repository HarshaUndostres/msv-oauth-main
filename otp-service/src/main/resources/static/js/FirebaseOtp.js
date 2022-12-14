import { getAuth, RecaptchaVerifier } from "firebase/auth";

function FirebaseOtpfunction(mobile,message,login){
    const auth = getAuth();
    auth.languageCode = 'it';
    window.recaptchaVerifier = new RecaptchaVerifier('sign-in-button', {
        'size': 'invisible',
        'callback': (response) => {
            // reCAPTCHA solved, allow signInWithPhoneNumber.
            onSignInSubmit();
        }
    }, auth);
    window.recaptchaVerifier = new RecaptchaVerifier('recaptcha-container', {}, auth);
    window.recaptchaVerifier = new RecaptchaVerifier('recaptcha-container', {
        'size': 'normal',
        'callback': (response) => {
            // reCAPTCHA solved, allow signInWithPhoneNumber.
            // ...
        },
        'expired-callback': () => {
            // Response expired. Ask user to solve reCAPTCHA again.
            // ...
        }
    }, auth);
    const phoneNumber = mobile;
    const appVerifier = window.recaptchaVerifier;

    signInWithPhoneNumber(auth, phoneNumber, appVerifier)
        .then((confirmationResult) => {
            // SMS sent. Prompt user to type the code from the message, then sign the
            // user in with confirmationResult.confirm(code).
            window.confirmationResult = confirmationResult;
            // ...
        }).catch((error) => {
        // Error; SMS not sent
        // ...
        grecaptcha.reset(window.recaptchaWidgetId);

        // Or, if you haven't stored the widget ID:
        window.recaptchaVerifier.render().then(function(widgetId) {
            grecaptcha.reset(widgetId);
        });
    });

    const code = getCodeFromUserInput();
    confirmationResult.confirm(code).then((result) => {
        // User signed in successfully.
        const user = result.user;
        // ...
    }).catch((error) => {
        // User couldn't sign in (bad verification code?)
        // ...
    });
}
