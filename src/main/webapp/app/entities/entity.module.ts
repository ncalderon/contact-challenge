import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'person',
                loadChildren: './person/person.module#ContactchallengePersonModule'
            },
            {
                path: 'person-contact',
                loadChildren: './person-contact/person-contact.module#ContactchallengePersonContactModule'
            },
            {
                path: 'address',
                loadChildren: './address/address.module#ContactchallengeAddressModule'
            },
            {
                path: 'person',
                loadChildren: './person/person.module#ContactchallengePersonModule'
            },
            {
                path: 'person-contact',
                loadChildren: './person-contact/person-contact.module#ContactchallengePersonContactModule'
            },
            {
                path: 'address',
                loadChildren: './address/address.module#ContactchallengeAddressModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ContactchallengeEntityModule {}
