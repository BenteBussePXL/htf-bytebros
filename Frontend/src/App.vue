<script setup lang="ts">
import HelloWorld from './components/HelloWorld.vue'
import TheWelcome from './components/TheWelcome.vue'
import questApiService from './questService'; // Import your API service

import { ref } from 'vue';

const isBruteForcing = ref(false);
const bruteForceResult = ref(null);
const bruteForceError = ref(null);

const runBruteForce = async () => {
  isBruteForcing.value = true;
  bruteForceError.value = null;

  try {
    const result = await questApiService.bruteForceQuest(); // Call the brute force function
    bruteForceResult.value = result ? result.passcode : null;
  } catch (error) {
    bruteForceError.value = 'Error occurred while brute-forcing.';
    console.error(error);
  } finally {
    isBruteForcing.value = false;
  }
};
</script>

<template>
  <header id="app-header">
    <img
      id="app-logo"
      alt="Vue logo"
      class="logo"
      src="./assets/logo.svg"
      width="125"
      height="125"
    />

    <div id="header-wrapper" class="wrapper">
      <HelloWorld id="hello-world" msg="You did it!" />
    </div>
  </header>

  <main id="app-main">
    <TheWelcome id="welcome-section" />

    <section id="brute-force-section">
      <h2>Brute Force the Passcode</h2>
      <button @click="runBruteForce" :disabled="isBruteForcing">
        {{ isBruteForcing ? "Brute-forcing..." : "Start Brute Force" }}
      </button>
      <div v-if="bruteForceResult" class="result">
        <p>Passcode found: {{ bruteForceResult }}</p>
      </div>
      <div v-if="bruteForceError" class="error">
        <p>{{ bruteForceError }}</p>
      </div>
    </section>
  </main>
</template>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

#brute-force-section {
  margin-top: 2rem;
}

button {
  padding: 0.5rem 1rem;
  font-size: 1rem;
  cursor: pointer;
}

.result {
  margin-top: 1rem;
  color: green;
}

.error {
  margin-top: 1rem;
  color: red;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
