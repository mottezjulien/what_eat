import DemoFilter from "./components/DemoFilter.vue";
import DemoGenerateButton from "./components/DemoGenerateButton.vue";
import DemoResult from "./components/DemoResult.vue";

export default {
  components: {
    DemoFilter,
    DemoGenerateButton,
    DemoResult,
  },
  data() {
    return {
      foundDishes: [],
    };
  },
  mounted() {
    this.initFilter();
    console.log("plop");
  },
  methods: {
    initFilter() {
      fetch("http://localhost:8080/api/demo/rule-groups/", {
        headers: { "Content-type": "application/json" },
      })
        .then((res) => res.json())
        .then((response) => {
          console.log({ response });
          //this.foundDishes = response;
        })
        .catch((error) => {
          console.log("Looks like there was a problem: \n", error);
        });
    },
    generate() {},
  },
};
