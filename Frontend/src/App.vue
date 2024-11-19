<script setup lang="ts">
import HelloWorld from './components/HelloWorld.vue'


import newline from './components/newline.vue';
</script>

<template>
  <header id="app-header">
    <div id="header-wrapper" class="wrapper">


      <HelloWorld/>
    </div>
  </header>

  <main id="app-main">
    <newline/>
  </main>
</template>

<style scoped>
</style>


<style scoped>
/* Example: Applying the Doto font with a unique class */
p, h1, h2, h3, h4, h5, h6{
  font-family: "VT323", monospace;
  font-weight: 400;
  font-style: normal;
  font-size: 2rem;
}

@media (min-width: 1024px) {
  .doto-example {
    font-weight: 700; /* Change weight for larger screens, if needed */
  }
}
</style>
