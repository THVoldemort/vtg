import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './hotel-my-suffix.reducer';
import { IHotelMySuffix } from 'app/shared/model/hotel-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IHotelMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IHotelMySuffixUpdateState {
  isNew: boolean;
}

export class HotelMySuffixUpdate extends React.Component<IHotelMySuffixUpdateProps, IHotelMySuffixUpdateState> {
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
      const { hotelEntity } = this.props;
      const entity = {
        ...hotelEntity,
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
    this.props.history.push('/entity/hotel-my-suffix');
  };

  render() {
    const { hotelEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vtgApp.hotel.home.createOrEditLabel">
              <Translate contentKey="vtgApp.hotel.home.createOrEditLabel">Create or edit a Hotel</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : hotelEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="hotel-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nameLabel" for="name">
                    <Translate contentKey="vtgApp.hotel.name">Name</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-name" type="text" name="name" />
                </AvGroup>
                <AvGroup>
                  <Label id="starRankLabel" for="starRank">
                    <Translate contentKey="vtgApp.hotel.starRank">Star Rank</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-starRank" type="number" className="form-control" name="starRank" />
                </AvGroup>
                <AvGroup>
                  <Label id="sloganLabel" for="slogan">
                    <Translate contentKey="vtgApp.hotel.slogan">Slogan</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-slogan" type="text" name="slogan" />
                </AvGroup>
                <AvGroup>
                  <Label id="addressLabel" for="address">
                    <Translate contentKey="vtgApp.hotel.address">Address</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-address" type="text" name="address" />
                </AvGroup>
                <AvGroup>
                  <Label id="priceFromLabel" for="priceFrom">
                    <Translate contentKey="vtgApp.hotel.priceFrom">Price From</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-priceFrom" type="number" className="form-control" name="priceFrom" />
                </AvGroup>
                <AvGroup>
                  <Label id="priceToLabel" for="priceTo">
                    <Translate contentKey="vtgApp.hotel.priceTo">Price To</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-priceTo" type="number" className="form-control" name="priceTo" />
                </AvGroup>
                <AvGroup>
                  <Label id="viewCountLabel" for="viewCount">
                    <Translate contentKey="vtgApp.hotel.viewCount">View Count</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-viewCount" type="number" className="form-control" name="viewCount" />
                </AvGroup>
                <AvGroup>
                  <Label id="convenientLabel" for="convenient">
                    <Translate contentKey="vtgApp.hotel.convenient">Convenient</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-convenient" type="number" className="form-control" name="convenient" />
                </AvGroup>
                <AvGroup>
                  <Label id="introductionLabel" for="introduction">
                    <Translate contentKey="vtgApp.hotel.introduction">Introduction</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-introduction" type="text" name="introduction" />
                </AvGroup>
                <AvGroup>
                  <Label id="confirmBookingTypeLabel" for="confirmBookingType">
                    <Translate contentKey="vtgApp.hotel.confirmBookingType">Confirm Booking Type</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-confirmBookingType" type="number" className="form-control" name="confirmBookingType" />
                </AvGroup>
                <AvGroup>
                  <Label id="ratingIdLabel" for="ratingId">
                    <Translate contentKey="vtgApp.hotel.ratingId">Rating Id</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-ratingId" type="number" className="form-control" name="ratingId" />
                </AvGroup>
                <AvGroup>
                  <Label id="latitudeLabel" for="latitude">
                    <Translate contentKey="vtgApp.hotel.latitude">Latitude</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-latitude" type="number" className="form-control" name="latitude" />
                </AvGroup>
                <AvGroup>
                  <Label id="longitudeLabel" for="longitude">
                    <Translate contentKey="vtgApp.hotel.longitude">Longitude</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-longitude" type="number" className="form-control" name="longitude" />
                </AvGroup>
                <AvGroup>
                  <Label id="provinceIdLabel" for="provinceId">
                    <Translate contentKey="vtgApp.hotel.provinceId">Province Id</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-provinceId" type="number" className="form-control" name="provinceId" />
                </AvGroup>
                <AvGroup>
                  <Label id="hotelTypeLabel">
                    <Translate contentKey="vtgApp.hotel.hotelType">Hotel Type</Translate>
                  </Label>
                  <AvInput
                    id="hotel-my-suffix-hotelType"
                    type="select"
                    className="form-control"
                    name="hotelType"
                    value={(!isNew && hotelEntity.hotelType) || 'KHACH_SAN'}
                  >
                    <option value="KHACH_SAN">
                      <Translate contentKey="vtgApp.HotelType.KHACH_SAN" />
                    </option>
                    <option value="NHA_NGHI">
                      <Translate contentKey="vtgApp.HotelType.NHA_NGHI" />
                    </option>
                    <option value="HOTEL">
                      <Translate contentKey="vtgApp.HotelType.HOTEL" />
                    </option>
                    <option value="HOMESTAY">
                      <Translate contentKey="vtgApp.HotelType.HOMESTAY" />
                    </option>
                    <option value="CAN_HO">
                      <Translate contentKey="vtgApp.HotelType.CAN_HO" />
                    </option>
                    <option value="RESOURCE">
                      <Translate contentKey="vtgApp.HotelType.RESOURCE" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="filePath1Label" for="filePath1">
                    <Translate contentKey="vtgApp.hotel.filePath1">File Path 1</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-filePath1" type="text" name="filePath1" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath2Label" for="filePath2">
                    <Translate contentKey="vtgApp.hotel.filePath2">File Path 2</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-filePath2" type="text" name="filePath2" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath3Label" for="filePath3">
                    <Translate contentKey="vtgApp.hotel.filePath3">File Path 3</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-filePath3" type="text" name="filePath3" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath4Label" for="filePath4">
                    <Translate contentKey="vtgApp.hotel.filePath4">File Path 4</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-filePath4" type="text" name="filePath4" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath5Label" for="filePath5">
                    <Translate contentKey="vtgApp.hotel.filePath5">File Path 5</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-filePath5" type="text" name="filePath5" />
                </AvGroup>
                <AvGroup>
                  <Label id="placeIdLabel" for="placeId">
                    <Translate contentKey="vtgApp.hotel.placeId">Place Id</Translate>
                  </Label>
                  <AvField id="hotel-my-suffix-placeId" type="number" className="form-control" name="placeId" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/hotel-my-suffix" replace color="info">
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
  hotelEntity: storeState.hotel.entity,
  loading: storeState.hotel.loading,
  updating: storeState.hotel.updating
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
)(HotelMySuffixUpdate);
