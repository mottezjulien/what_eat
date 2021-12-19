
const URL_MENU = "/api/menus/";
const URL_DISH = "/api/dishes/";

var webVue = null;

webVue = new Vue({
    el: '#app',
    data: {
        menus : [],
        dishes : [],
        selectedMenu : null
    },
    mounted: function () {
        this.findAllMenus();
        //this.findAllDishes();
    },
    methods: {
        findAllMenus: async function () {
            let response = await axios.get(URL_MENU + "week/current/")
            this.menus = response.data.menus;
        },
        /*findAllDishes: function () {
            axios.get(URL_DISH + "type/final/").then(function (response) {
                webVue.dishes = response.data;
            }).catch(function (error) {
                console.log(error);
            });
        },*/
        create: async function() {
            let response = await axios.post(URL_MENU + "week/current/menu/now/");
            this.menus.push(response.data);
        },
        selectMenu: function(menu) {
            this.selectedMenu = menu;
        }
      }
});