import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPersonContact } from 'app/shared/model/person-contact.model';

@Component({
    selector: 'jhi-person-contact-detail',
    templateUrl: './person-contact-detail.component.html'
})
export class PersonContactDetailComponent implements OnInit {
    personContact: IPersonContact;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ personContact }) => {
            this.personContact = personContact;
        });
    }

    previousState() {
        window.history.back();
    }
}
