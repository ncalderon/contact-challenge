import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IPersonContact } from 'app/shared/model/person-contact.model';
import { PersonContactService } from './person-contact.service';
import { IPerson } from 'app/shared/model/person.model';
import { PersonService } from 'app/entities/person';

@Component({
    selector: 'jhi-person-contact-update',
    templateUrl: './person-contact-update.component.html'
})
export class PersonContactUpdateComponent implements OnInit {
    personContact: IPersonContact;
    isSaving: boolean;

    people: IPerson[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected personContactService: PersonContactService,
        protected personService: PersonService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ personContact }) => {
            this.personContact = personContact;
        });
        this.personService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IPerson[]>) => mayBeOk.ok),
                map((response: HttpResponse<IPerson[]>) => response.body)
            )
            .subscribe((res: IPerson[]) => (this.people = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.personContact.id !== undefined) {
            this.subscribeToSaveResponse(this.personContactService.update(this.personContact));
        } else {
            this.subscribeToSaveResponse(this.personContactService.create(this.personContact));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersonContact>>) {
        result.subscribe((res: HttpResponse<IPersonContact>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackPersonById(index: number, item: IPerson) {
        return item.id;
    }
}
