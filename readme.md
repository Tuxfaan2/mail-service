# Mail-Service

This service is used to send mails from a json-request.

### Development-Configuration:

For development-configuration you can add an "application-development.yml" file,
which is ignored by the git.

```
mail-service:
  mail-password:
  mail-user:
```

### Additional information:

This service is used for sending mails from a gmail
and because Google doesn't allow you to log in with your
default password to third party apps, you have to generate an app-password.

#### Here is how to do it:
https://stackoverflow.com/questions/26594097/javamail-exception-javax-mail-authenticationfailedexception-534-5-7-9-applicatio/72592946#72592946


