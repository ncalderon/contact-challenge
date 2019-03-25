import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ContactchallengeSharedModule } from 'app/shared';
import {
    PersonContactComponent,
    PersonContactDetailComponent,
    PersonContactUpdateComponent,
    PersonContactDeletePopupComponent,
    PersonContactDeleteDialogComponent,
    personContactRoute,
    personContactPopupRoute
} from './';

const ENTITY_STATES = [...personContactRoute, ...personContactPopupRoute];

@NgModule({
    imports: [ContactchallengeSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PersonContactComponent,
        PersonContactDetailComponent,
        PersonContactUpdateComponent,
        PersonContactDeleteDialogComponent,
        PersonContactDeletePopupComponent
    ],
    entryComponents: [
        PersonContactComponent,
        PersonContactUpdateComponent,
        PersonContactDeleteDialogComponent,
        PersonContactDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ContactchallengePersonContactModule {}
