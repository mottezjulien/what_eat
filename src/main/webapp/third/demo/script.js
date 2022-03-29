
const URL = "/api/rules/examples/";

var webVue

webVue = new Vue({
    el: '#app',
    data: {
        rules : []
    },
    mounted: function () {
        this.findRules()
    },
    methods: {
        findRules: async function () {
            const response = await axios.get(URL)
            this.rules = response.data
            console.log(this.rules)
        },
        generate: async function () {
            const response = await axios.post(URL)
            console.lg(response.data)
        }

      }
});