
<script lang="ts">

import { defineComponent } from 'vue'

export default defineComponent({
    props: ['dishes'],
    data() {
        return {
        }
    },
    methods: {

    }
})

</script>

<template>
    <div class="row row-cols-1 row-cols-md-3 mb-3 text-center">
      <div class="col" v-for="(each) in dishes">
        <div class="card mb-4 rounded-3 shadow-sm">
          <div class="card-header py-3">
            <h5 class="my-0 fw-normal">{{each.label}}</h5>
          </div>
          <div class="card-body">
            <p>Détails (to do) ...</p>
            <button type="button" class="w-100 btn btn-lg btn-outline-primary">Sign up for free</button>
          </div>
        </div>
      </div>
    </div>
</template>

<style scoped>
</style>
