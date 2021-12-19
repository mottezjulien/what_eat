
const URL_MENU = "/api/menus/";
const URL_DISH = "/api/dishes/";

var webVue = null;

webVue = new Vue({
    el: '#app',
    data: {
        menus : []
    },
    mounted: function () {
        this.findAllMenus();
    },
    methods: {
        findAllMenus: async function () {
            let response = await axios.get(URL_MENU + "week/current/")
            this.menus = response.data.menus.map(menu => this.plop(menu));
        },
        plop: function(menu) {
            let displayLabel = "Non défini";
            if(!menu.displayLabel) {
                displayLabel = menu.displayLabel;
            }
            return {
                "date" : menu.date,
                "displayLabel" : displayLabel
            }
        }
        /*create: async function() {
            let response = await axios.post(URL_MENU + "week/current/menu/now/");
            this.menus.push(response.data);
        },
        selectMenu: function(menu) {
            this.selectedMenu = menu;
        }*/
      }
});