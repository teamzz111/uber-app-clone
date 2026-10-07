# Uber App Clone (Android)

A ride-hailing app prototype for Android, inspired by Uber, written in Java with Firebase and Google Maps. It covers the basic building blocks of such an app: passenger and driver sign-up, authentication (including fingerprint login), a map with the user's location and a payment card form.

> **Context:** university project (2020) built by a student team at Universidad Libre. It is kept here as a portfolio reference and is no longer maintained.

## Features

- **Authentication** with Firebase Auth (email/password), a persisted session check, and **biometric (fingerprint) login** via `androidx.biometric`.
- **Passenger registration** and a separate **driver registration** flow: personal details, vehicle brand selection, and upload of the driver's documents to Firebase Storage, with records saved in Cloud Firestore.
- **Map home screen** (Google Maps SDK) that locates the user with the Fused Location Provider (runtime permissions via Dexter) and applies a custom map style.
- **Payments section** with a credit-card entry form (Braintree `card-form`).
- Splash screen and navigation-drawer layout.

## Tech stack

Java · Android SDK (minSdk 21, targetSdk 29) · Firebase (Auth, Firestore, Storage, Analytics; Cloud Messaging dependency) · Google Maps & Location · AndroidX Biometric · Braintree card-form · Dexter · Gradle

## Running locally

1. Open the project in Android Studio.
2. Use your own Firebase project: download its `google-services.json` into `app/`, and enable Email/Password auth, Firestore and Storage.
3. Add your own Google Maps API key in `app/src/debug/res/values/google_maps_api.xml` (and the release equivalent).
4. Build and run on a device or emulator with Google Play Services.

Active development happened on the `develop` branch.

## Team

Andrés Largo ([@teamzz111](https://github.com/teamzz111)), Jorge Morales ([@JorgeAMS](https://github.com/JorgeAMS)), Erika Infante ([@ErInfante](https://github.com/ErInfante)) and Santiago Jara González ([@sjg99](https://github.com/sjg99)).
