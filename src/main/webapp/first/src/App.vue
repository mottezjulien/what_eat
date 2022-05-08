<script setup lang="ts">
    import { RouterLink, RouterView } from 'vue-router'
    import HeaderComponent from '@/components/Header/Header.vue'
</script>

<template>
    <main>
        <header class="p-3 mb-3 border-bottom">
            <HeaderComponent />
        </header>
        <div class="container-fluid">
            <RouterView />
        </div>
    </main>
</template>

<style>

</style>
