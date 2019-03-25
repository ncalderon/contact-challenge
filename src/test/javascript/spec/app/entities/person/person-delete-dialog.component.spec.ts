/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ContactchallengeTestModule } from '../../../test.module';
import { PersonDeleteDialogComponent } from 'app/entities/person/person-delete-dialog.component';
import { PersonService } from 'app/entities/person/person.service';

describe('Component Tests', () => {
    describe('Person Management Delete Component', () => {
        let comp: PersonDeleteDialogComponent;
        let fixture: ComponentFixture<PersonDeleteDialogComponent>;
        let service: PersonService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ContactchallengeTestModule],
                declarations: [PersonDeleteDialogComponent]
            })
                .overrideTemplate(PersonDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PersonDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PersonService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
