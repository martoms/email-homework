import type { App } from 'vue';
import VAnchor from '@/components/atoms/VAnchor.vue';
import VH1 from '@/components/atoms/VH1.vue';
import VH2 from '@/components/atoms/VH2.vue';
import VH3 from '@/components/atoms/VH3.vue';
import VTextP from '@/components/atoms/VTextP.vue';
import VUList from '@/components/atoms/VUList.vue';
import VList from '@/components/atoms/VList.vue';
import VForm from '@/components/atoms/VForm.vue';
import VFormInput from '@/components/atoms/VFormInput.vue';
import VFormTextarea from '@/components/atoms/VFormTextarea.vue';
import VFormLabel from '@/components/atoms/VFormLabel.vue';
import VFormSelect from '@/components/atoms/VFormSelect.vue';
import VButton from '@/components/atoms/VButton.vue';

export default {
  install(app: App, options?: any) {
    app.component('VAnchor', VAnchor)
    app.component('VH1', VH1)
    app.component('VH2', VH2)
    app.component('VH3', VH3)
    app.component('VTextP', VTextP)
    app.component('VUList', VUList)
    app.component('VList', VList)
    app.component('VForm', VForm)
    app.component('VFormInput', VFormInput)
    app.component('VFormTextarea', VFormTextarea)
    app.component('VFormLabel', VFormLabel)
    app.component('VFormSelect', VFormSelect)
    app.component('VButton', VButton)
  }
}