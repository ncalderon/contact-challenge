import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ContactchallengeSharedModule } from 'app/shared';
import {
    AddressComponent,
    AddressDetailComponent,
    AddressUpdateComponent,
    AddressDeletePopupComponent,
    AddressDeleteDialogComponent,
    addressRoute,
    addressPopupRoute
} from './';

const ENTITY_STATES = [...addressRoute, ...addressPopupRoute];

@NgModule({
    imports: [ContactchallengeSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AddressComponent,
        AddressDetailComponent,
        AddressUpdateComponent,
        AddressDeleteDialogComponent,
        AddressDeletePopupComponent
    ],
    entryComponents: [AddressComponent, AddressUpdateComponent, AddressDeleteDialogComponent, AddressDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ContactchallengeAddressModule {}
