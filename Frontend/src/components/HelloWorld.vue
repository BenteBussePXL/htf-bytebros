<script setup lang="ts">
</script>

<template>
  <div class="greetings">

    <img
      id="app-logo"
      alt="Vue logo"
      class="logo"
      src="./../assets/logo.png"
      width="150"
      height="150"
    />
    <div>
      <h1 class="green">Collide Space Center terminal</h1>
    <h3>
      <p>Destroy the aliens with commands</p>
      <p>Enter "help" for more information</p>
      <p>Made by ByteBros</p>
    </h3>
  </div>
  </div>
</template>

<style scoped>
.greetings {
  margin-top: 75px;
  margin-left: 50px;
  display: flex;
  align-items: center; /* Align vertically */
  gap: 20px; /* Optional: Adjust the space between the elements */
}

.green {
  color: limegreen;
}

h3 {
  color: grey;
}


.greetings h1{
  font-size: 3rem;

}

p, h1, h2, h3, h4, h5, h6{
  font-family: "VT323", monospace;
  font-weight: 400;
  font-style: normal;
  font-size: 1.8rem;
}

</style>
