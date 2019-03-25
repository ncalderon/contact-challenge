/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ContactchallengeTestModule } from '../../../test.module';
import { AddressDeleteDialogComponent } from 'app/entities/address/address-delete-dialog.component';
import { AddressService } from 'app/entities/address/address.service';

describe('Component Tests', () => {
    describe('Address Management Delete Component', () => {
        let comp: AddressDeleteDialogComponent;
        let fixture: ComponentFixture<AddressDeleteDialogComponent>;
        let service: AddressService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ContactchallengeTestModule],
                declarations: [AddressDeleteDialogComponent]
            })
                .overrideTemplate(AddressDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AddressDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AddressService);
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
