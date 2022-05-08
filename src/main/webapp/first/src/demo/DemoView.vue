<script lang="ts" src="./demo.controller.ts" />

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
          <DemoGenerateButton @click="generate()" />
        </div>
        <div class="col-1"></div>
      </div>
    <div class="row pt-2">
    <div class="col-1"></div>
    <div class="col">
      <DemoResult :dishes="foundDishes" />
    </div>
    <div class="col-1"></div>
    </div>
</template>



