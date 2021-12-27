
const URL_MENU_SCHEDULE_ROOT = "/api/menus/schedule/";
const URL_MENU_SCHEDULE_CURRENT = URL_MENU_SCHEDULE_ROOT + "current/";


var webVue

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
            let response = await axios.get(URL_MENU_SCHEDULE_CURRENT)
            this.menus = response.data.menus
        },
        shouldGenerate: function (menu) {
            return this.generateLink(menu);
        },
        generate: async function (menu) {
            const link = this.generateLink(menu)
            let response = await axios({
                method: link.method,
                url: link.url
            });
            console.log(response)
        },
        generateLink: function (menu) {
            return menu._links.find(link => link.id == 'generate')
        }

        /*plop: function(menu) {
            let displayLabel = "Non défini";
            if(menu.dishLabel) {
                displayLabel = menu.dishLabel;
            }
            return {
                "date" : menu.date,
                "dish" : {
                    "label" : displayLabel
                }
            }
        }*/

        /*create: async function() {
            let response = await axios.post(URL_MENU + "week/current/menu/now/");
            this.menus.push(response.data);
        },
        selectMenu: function(menu) {
            this.selectedMenu = menu;
        }*/
      }
});