import { NgModule } from '@angular/core';

import { ContactchallengeSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [ContactchallengeSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [ContactchallengeSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class ContactchallengeSharedCommonModule {}
