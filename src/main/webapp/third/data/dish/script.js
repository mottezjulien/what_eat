
const URL = "/api/data/dish/";

var webVueDishList
var webVueDishDetails

webVueDishList = new Vue({
    el: '#container-dish-list',
    data: {
        dishes : []
    },
    mounted: function () {
        this.findDishes()
    },
    methods: {
        findDishes: async function () {
            const response = await axios.get(URL)
            this.dishes = response.data
        },
        details: function (dish) {
            webVueDishDetails.show({ ...dish });
        }
      }
});

webVueDishDetails = new Vue({
    el: '#container-dish-details',
    data: {
        seen: false,
        dish : {}
    },
    methods: {
        show: async function (dish) {
            const response = await axios.get(URL + dish.id)
            this.dish = response.data;
            this.seen = true;
        },
        hide: function () {
            this.seen = false;
        },
        submit: function () {

        }
    }
});
