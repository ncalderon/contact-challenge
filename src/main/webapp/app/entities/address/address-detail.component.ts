import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAddress } from 'app/shared/model/address.model';

@Component({
    selector: 'jhi-address-detail',
    templateUrl: './address-detail.component.html'
})
export class AddressDetailComponent implements OnInit {
    address: IAddress;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ address }) => {
            this.address = address;
        });
    }

    previousState() {
        window.history.back();
    }
}
