<script lang="ts">
export default {
  props: ['path'],
    data() {
      return {
        active: false,
        mouse: false,
      };
    },
  name: "HeaderVueLink",
    watch: {
      $route(to, from) {
        this.active = (to.path == this.path);
      },
    },
};
</script>

<template>
    <RouterLink
        class="nav-link px-2"
        :to="path"
        v-bind:class="{
            'link-primary': this.active || this.mouse,
            'link-dark': !this.active && !this.mouse }"
        @mouseover="this.mouse = true"
        @mouseout="this.mouse = false"
            >
        <slot></slot>
    </RouterLink>
</template>
