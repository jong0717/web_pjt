<template>
  <div>
    <!-- <b-form-group label="Table Options" label-cols-lg="2">
      <b-form-checkbox v-model="striped" inline>Striped</b-form-checkbox>
      <b-form-checkbox v-model="bordered" inline>Bordered</b-form-checkbox>
      <b-form-checkbox v-model="borderless" inline>Borderless</b-form-checkbox>
      <b-form-checkbox v-model="outlined" inline>Outlined</b-form-checkbox>
      <b-form-checkbox v-model="small" inline>Small</b-form-checkbox>
      <b-form-checkbox v-model="hover" inline>Hover</b-form-checkbox>
      <b-form-checkbox v-model="dark" inline>Dark</b-form-checkbox>
      <b-form-checkbox v-model="fixed" inline>Fixed</b-form-checkbox>
      <b-form-checkbox v-model="footClone" inline>Foot Clone</b-form-checkbox>
      <b-form-checkbox v-model="noCollapse" inline>No border collapse</b-form-checkbox>
    </b-form-group>-->
    <b-form-group label="Head Variant" label-cols-lg="2">
      <b-form-radio-group v-model="headVariant" class="mt-lg-2">
        <b-form-radio :value="null" inline>None</b-form-radio>
        <b-form-radio value="light" inline>Light</b-form-radio>
        <b-form-radio value="dark" inline>Dark</b-form-radio>
      </b-form-radio-group>
    </b-form-group>
    <b-form-group label="Table Variant" label-for="table-style-variant" label-cols-lg="2">
      <b-form-select v-model="tableVariant" :options="tableVariants" id="table-style-variant">
        <template v-slot:first>
          <option value>-- None --</option>
        </template>
      </b-form-select>
    </b-form-group>

    <!-- <b-table
      :striped="striped"
      :bordered="bordered"
      :borderless="borderless"
      :outlined="outlined"
      :small="small"
      :hover="hover"
      :dark="dark"
      :fixed="fixed"
      :foot-clone="footClone"
      :no-border-collapse="noCollapse"
      :items="items"
      :fields="fields"
      :head-variant="headVariant"
      :table-variant="tableVariant"
    ></b-table>-->
    <b-table :bordered="bordered" :small="small" :hover="hover" :items="items" :fields="fields"
    :head-variant="headVariant"
    :table-variant="tableVariant">
    
    </b-table>
  </div>
  <!-- <div >
          <table class="table table-sm">
            <thead>
              <tr>
                <th scope="col">블로그이름</th>
                <th scope="col">테마</th>
              </tr>
            </thead>
            <tbody class="table" v-for='item in myblog' :key="item">
              <tr>
                <a style="color:black !important" @click="moveToBlog(item)"><td>{{ item.blogname }}</td></a>
                <td>{{ item.template_num }}</td>
              </tr>
            </tbody>
          </table>
  </div>-->
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  data() {
    return {
      fields: ["블로그이름", "테마", "바로가기"],
      items: [
        { 바로가기: 40, 블로그이름: "Dickerson", 테마: "Macdonald" },
        { 바로가기: 21, 블로그이름: "Larsen", 테마: "Shaw" },
        { 바로가기: 89, 블로그이름: "Geneva", 테마: "Wilson" },
      ],
      tableVariants: [
        "primary",
        "secondary",
        "info",
        "danger",
        "warning",
        "success",
        "light",
        "dark",
      ],
      striped: false,
      bordered: true,
      borderless: false,
      outlined: false,
      small: true,
      hover: true,
      dark: false,
      fixed: false,
      footClone: false,
      headVariant: null,
      tableVariant: "",
      noCollapse: false,
    };
  },
  methods: {
    ...mapActions(["moveToBlog"]),
  },
  computed: {
    ...mapState(["myblog"]),
  },
};
</script>