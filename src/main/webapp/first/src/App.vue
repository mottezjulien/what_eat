<script setup lang="ts">
    import { RouterLink, RouterView } from 'vue-router'
    import Header from '@/components/Header.vue'
</script>

<template>
    <main>
        <header class="p-3 mb-3 border-bottom">
            <Header />
        </header>
        <div class="container-fluid">
            <RouterView />
        </div>
    </main>
</template>

<style>

</style>
