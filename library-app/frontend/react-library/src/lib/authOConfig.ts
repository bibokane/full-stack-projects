export const auth0Config = {
 clientId: 'WknKAFJbq9UDgMQ8LIo7D7oKIebRzSIb',
 issuer: "dev-pvwvg60igytpqvpk.eu.auth0.com",
 audience: "http://localhost:8080",
 redirectUri: window.location.origin+"/login/callback",
 scope: 'openid profile email'
}