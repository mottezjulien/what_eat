<script lang="ts">

import DemoFilter from '@/components/DemoFilter.vue'
import DemoGenerateButton from '@/components/DemoGenerateButton.vue'
import DemoResult from '@/components/DemoResult.vue'

import { defineComponent } from 'vue'

export default defineComponent({
  components: {
    DemoFilter, DemoGenerateButton, DemoResult
  },
  data() {
    return {
    }
  },
 mounted() {
   fetch('https://api.openbrewerydb.org/breweries/banjo-brewing-fayetteville', {
       headers: { 'Content-type': 'application/json' },
   }).then(res=>res.json()).then((response) => {
       console.log({ response })
   }).catch((error) => {
       console.log('Looks like there was a problem: \n', error);
   });
 }
})
</script>

<template>
  <div class="row">
      <div class="col-1"></div>
      <div class="col">
        <DemoFilter />
      </div>
      <div class="col-1"></div>
  </div>
  <div class="row pt-2">
        <div class="col-1"></div>
        <div class="col">
          <DemoGenerateButton />
        </div>
        <div class="col-1"></div>
      </div>
    <div class="row pt-2">
    <div class="col-1"></div>
    <div class="col">
      <DemoResult />
    </div>
    <div class="col-1"></div>
    </div>
</template>



