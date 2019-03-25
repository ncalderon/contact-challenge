import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ContactchallengeSharedModule } from 'app/shared';
import {
    PersonComponent,
    PersonDetailComponent,
    PersonUpdateComponent,
    PersonDeletePopupComponent,
    PersonDeleteDialogComponent,
    personRoute,
    personPopupRoute
} from './';

const ENTITY_STATES = [...personRoute, ...personPopupRoute];

@NgModule({
    imports: [ContactchallengeSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [PersonComponent, PersonDetailComponent, PersonUpdateComponent, PersonDeleteDialogComponent, PersonDeletePopupComponent],
    entryComponents: [PersonComponent, PersonUpdateComponent, PersonDeleteDialogComponent, PersonDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ContactchallengePersonModule {}
