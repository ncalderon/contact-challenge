import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PersonContact } from 'app/shared/model/person-contact.model';
import { PersonContactService } from './person-contact.service';
import { PersonContactComponent } from './person-contact.component';
import { PersonContactDetailComponent } from './person-contact-detail.component';
import { PersonContactUpdateComponent } from './person-contact-update.component';
import { PersonContactDeletePopupComponent } from './person-contact-delete-dialog.component';
import { IPersonContact } from 'app/shared/model/person-contact.model';

@Injectable({ providedIn: 'root' })
export class PersonContactResolve implements Resolve<IPersonContact> {
    constructor(private service: PersonContactService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPersonContact> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PersonContact>) => response.ok),
                map((personContact: HttpResponse<PersonContact>) => personContact.body)
            );
        }
        return of(new PersonContact());
    }
}

export const personContactRoute: Routes = [
    {
        path: '',
        component: PersonContactComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'PersonContacts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PersonContactDetailComponent,
        resolve: {
            personContact: PersonContactResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PersonContacts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PersonContactUpdateComponent,
        resolve: {
            personContact: PersonContactResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PersonContacts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PersonContactUpdateComponent,
        resolve: {
            personContact: PersonContactResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PersonContacts'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const personContactPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PersonContactDeletePopupComponent,
        resolve: {
            personContact: PersonContactResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PersonContacts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
