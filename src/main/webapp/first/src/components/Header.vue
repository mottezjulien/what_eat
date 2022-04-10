<script setup lang="ts">

</script>

<template>
 <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
              <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
              </a>
              <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><RouterLink class="nav-link px-2 link-dark" to="/about">A propos</RouterLink></li>
                <li><RouterLink class="nav-link px-2 link-dark" to="/demo">Démo</RouterLink></li>
                <li><RouterLink class="nav-link px-2 link-secondary" to="/">Home</RouterLink></li>
              </ul>
              <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
              </form>
            </div>
        </div>
</template>
