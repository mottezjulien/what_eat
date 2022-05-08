<script lang="ts">

import HeaderVueLink from "./HeaderLink.vue";

export default {
    components: {
      HeaderVueLink
    },
  name: "HeaderVue",
};
</script>

<template>
  <div class="container">
    <div
      class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start"
    >
      <a
        href="/"
        class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none"
      >
        <svg
          class="bi me-2"
          width="40"
          height="32"
          role="img"
          aria-label="Bootstrap"
        >
          <use xlink:href="#bootstrap"></use>
        </svg>
      </a>
      <ul
        class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0"
      >
        <li>
            <HeaderVueLink path="/">Accueil</HeaderVueLink>
        </li>
        <li>
            <HeaderVueLink path="/demo">Démonstration</HeaderVueLink>
        </li>
        <li>
            <HeaderVueLink path="/about">A propos</HeaderVueLink>
        </li>
      </ul>
    </div>
  </div>
</template>
