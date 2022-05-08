
<template>

<div class="accordion" id="filterAccordion">
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingOne">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#filterContainer" aria-expanded="true" aria-controls="filterContainer">
        Filtre de recherche
      </button>
    </h2>
    <div id="filterContainer" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#filterAccordion">
      <div class="accordion-body">
        <form>
        <fieldset class="row mb-3">
                  <legend class="col-form-label col-sm-2 pt-0">Profil</legend>
                  <div class="col-sm-10">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="inputProfil" id="inputProfilFamilial" value="ABC" checked>
                        <label class="form-check-label" for="inputProfilFamilial">Familial</label>
                      </div>
                      <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="inputProfil" id="inputProfilVege" value="DEF">
                        <label class="form-check-label" for="inputProfilVege">Végétarien</label>
                      </div>
                      <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="inputProfil" id="inputProfileStu" value="IJK">
                        <label class="form-check-label" for="inputProfileStu">Étudiant</label>
                      </div>
                      <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="inputProfil" id="inputProfilePerso" disabled>
                        <label class="form-check-label" for="inputProfilePerso">Personnalisé</label>
                      </div>
                  </div>
                </fieldset>
                <div class="row mb-3">
                  <legend class="col-form-label col-sm-2 pt-0">Nb repas</legend>
                  <div class="col-sm-10">
                    <label class="form-label">5</label>
                  </div>
                </div>
                <div class="row mb-3">
                  <div class="col-sm-10 offset-sm-2">
                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="gridCheck1" disabled>
                      <label class="form-check-label" for="gridCheck1">
                        Accepte les doublons
                      </label>
                    </div>
                  </div>
                </div>
        </form>
      </div>
    </div>
  </div>
</div>

</template>

<script lang="ts">

import { defineComponent } from 'vue'

export default defineComponent({
    data() {
        return {
        }
    },
    methods: {

    }
})

</script>

<style scoped>
</style>
