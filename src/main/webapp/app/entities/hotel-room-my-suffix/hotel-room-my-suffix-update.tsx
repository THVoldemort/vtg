import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './hotel-room-my-suffix.reducer';
import { IHotelRoomMySuffix } from 'app/shared/model/hotel-room-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IHotelRoomMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IHotelRoomMySuffixUpdateState {
  isNew: boolean;
}

export class HotelRoomMySuffixUpdate extends React.Component<IHotelRoomMySuffixUpdateProps, IHotelRoomMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { hotelRoomEntity } = this.props;
      const entity = {
        ...hotelRoomEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/hotel-room-my-suffix');
  };

  render() {
    const { hotelRoomEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vtgApp.hotelRoom.home.createOrEditLabel">
              <Translate contentKey="vtgApp.hotelRoom.home.createOrEditLabel">Create or edit a HotelRoom</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : hotelRoomEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="hotel-room-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="typeLabel" for="type">
                    <Translate contentKey="vtgApp.hotelRoom.type">Type</Translate>
                  </Label>
                  <AvField id="hotel-room-my-suffix-type" type="number" className="form-control" name="type" />
                </AvGroup>
                <AvGroup>
                  <Label id="introductionLabel" for="introduction">
                    <Translate contentKey="vtgApp.hotelRoom.introduction">Introduction</Translate>
                  </Label>
                  <AvField id="hotel-room-my-suffix-introduction" type="text" name="introduction" />
                </AvGroup>
                <AvGroup>
                  <Label id="numOfAdultLabel" for="numOfAdult">
                    <Translate contentKey="vtgApp.hotelRoom.numOfAdult">Num Of Adult</Translate>
                  </Label>
                  <AvField id="hotel-room-my-suffix-numOfAdult" type="number" className="form-control" name="numOfAdult" />
                </AvGroup>
                <AvGroup>
                  <Label id="numOfChildLabel" for="numOfChild">
                    <Translate contentKey="vtgApp.hotelRoom.numOfChild">Num Of Child</Translate>
                  </Label>
                  <AvField id="hotel-room-my-suffix-numOfChild" type="number" className="form-control" name="numOfChild" />
                </AvGroup>
                <AvGroup>
                  <Label id="priceEstLabel" for="priceEst">
                    <Translate contentKey="vtgApp.hotelRoom.priceEst">Price Est</Translate>
                  </Label>
                  <AvField id="hotel-room-my-suffix-priceEst" type="number" className="form-control" name="priceEst" />
                </AvGroup>
                <AvGroup>
                  <Label id="priceActLabel" for="priceAct">
                    <Translate contentKey="vtgApp.hotelRoom.priceAct">Price Act</Translate>
                  </Label>
                  <AvField id="hotel-room-my-suffix-priceAct" type="number" className="form-control" name="priceAct" />
                </AvGroup>
                <AvGroup>
                  <Label id="quantityLabel" for="quantity">
                    <Translate contentKey="vtgApp.hotelRoom.quantity">Quantity</Translate>
                  </Label>
                  <AvField id="hotel-room-my-suffix-quantity" type="number" className="form-control" name="quantity" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath1Label" for="filePath1">
                    <Translate contentKey="vtgApp.hotelRoom.filePath1">File Path 1</Translate>
                  </Label>
                  <AvField id="hotel-room-my-suffix-filePath1" type="text" name="filePath1" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath2Label" for="filePath2">
                    <Translate contentKey="vtgApp.hotelRoom.filePath2">File Path 2</Translate>
                  </Label>
                  <AvField id="hotel-room-my-suffix-filePath2" type="text" name="filePath2" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath3Label" for="filePath3">
                    <Translate contentKey="vtgApp.hotelRoom.filePath3">File Path 3</Translate>
                  </Label>
                  <AvField id="hotel-room-my-suffix-filePath3" type="text" name="filePath3" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath4Label" for="filePath4">
                    <Translate contentKey="vtgApp.hotelRoom.filePath4">File Path 4</Translate>
                  </Label>
                  <AvField id="hotel-room-my-suffix-filePath4" type="text" name="filePath4" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath5Label" for="filePath5">
                    <Translate contentKey="vtgApp.hotelRoom.filePath5">File Path 5</Translate>
                  </Label>
                  <AvField id="hotel-room-my-suffix-filePath5" type="text" name="filePath5" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/hotel-room-my-suffix" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  hotelRoomEntity: storeState.hotelRoom.entity,
  loading: storeState.hotelRoom.loading,
  updating: storeState.hotelRoom.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HotelRoomMySuffixUpdate);
