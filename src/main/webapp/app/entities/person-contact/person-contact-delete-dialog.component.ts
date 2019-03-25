import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPersonContact } from 'app/shared/model/person-contact.model';
import { PersonContactService } from './person-contact.service';

@Component({
    selector: 'jhi-person-contact-delete-dialog',
    templateUrl: './person-contact-delete-dialog.component.html'
})
export class PersonContactDeleteDialogComponent {
    personContact: IPersonContact;

    constructor(
        protected personContactService: PersonContactService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.personContactService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'personContactListModification',
                content: 'Deleted an personContact'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-person-contact-delete-popup',
    template: ''
})
export class PersonContactDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ personContact }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PersonContactDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.personContact = personContact;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/person-contact', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/person-contact', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
