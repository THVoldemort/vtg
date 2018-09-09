import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './place-my-suffix.reducer';
import { IPlaceMySuffix } from 'app/shared/model/place-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IPlaceMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IPlaceMySuffixUpdateState {
  isNew: boolean;
}

export class PlaceMySuffixUpdate extends React.Component<IPlaceMySuffixUpdateProps, IPlaceMySuffixUpdateState> {
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
      const { placeEntity } = this.props;
      const entity = {
        ...placeEntity,
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
    this.props.history.push('/entity/place-my-suffix');
  };

  render() {
    const { placeEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vtgApp.place.home.createOrEditLabel">
              <Translate contentKey="vtgApp.place.home.createOrEditLabel">Create or edit a Place</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : placeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="place-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nameLabel" for="name">
                    <Translate contentKey="vtgApp.place.name">Name</Translate>
                  </Label>
                  <AvField id="place-my-suffix-name" type="text" name="name" />
                </AvGroup>
                <AvGroup>
                  <Label id="sloganLabel" for="slogan">
                    <Translate contentKey="vtgApp.place.slogan">Slogan</Translate>
                  </Label>
                  <AvField id="place-my-suffix-slogan" type="text" name="slogan" />
                </AvGroup>
                <AvGroup>
                  <Label id="addressLabel" for="address">
                    <Translate contentKey="vtgApp.place.address">Address</Translate>
                  </Label>
                  <AvField id="place-my-suffix-address" type="text" name="address" />
                </AvGroup>
                <AvGroup>
                  <Label id="ratingIdLabel" for="ratingId">
                    <Translate contentKey="vtgApp.place.ratingId">Rating Id</Translate>
                  </Label>
                  <AvField id="place-my-suffix-ratingId" type="number" className="form-control" name="ratingId" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath1Label" for="filePath1">
                    <Translate contentKey="vtgApp.place.filePath1">File Path 1</Translate>
                  </Label>
                  <AvField id="place-my-suffix-filePath1" type="text" name="filePath1" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath2Label" for="filePath2">
                    <Translate contentKey="vtgApp.place.filePath2">File Path 2</Translate>
                  </Label>
                  <AvField id="place-my-suffix-filePath2" type="text" name="filePath2" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath3Label" for="filePath3">
                    <Translate contentKey="vtgApp.place.filePath3">File Path 3</Translate>
                  </Label>
                  <AvField id="place-my-suffix-filePath3" type="text" name="filePath3" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath4Label" for="filePath4">
                    <Translate contentKey="vtgApp.place.filePath4">File Path 4</Translate>
                  </Label>
                  <AvField id="place-my-suffix-filePath4" type="text" name="filePath4" />
                </AvGroup>
                <AvGroup>
                  <Label id="filePath5Label" for="filePath5">
                    <Translate contentKey="vtgApp.place.filePath5">File Path 5</Translate>
                  </Label>
                  <AvField id="place-my-suffix-filePath5" type="text" name="filePath5" />
                </AvGroup>
                <AvGroup>
                  <Label id="introductionLabel" for="introduction">
                    <Translate contentKey="vtgApp.place.introduction">Introduction</Translate>
                  </Label>
                  <AvField id="place-my-suffix-introduction" type="text" name="introduction" />
                </AvGroup>
                <AvGroup>
                  <Label id="priceFromLabel" for="priceFrom">
                    <Translate contentKey="vtgApp.place.priceFrom">Price From</Translate>
                  </Label>
                  <AvField id="place-my-suffix-priceFrom" type="number" className="form-control" name="priceFrom" />
                </AvGroup>
                <AvGroup>
                  <Label id="priceToLabel" for="priceTo">
                    <Translate contentKey="vtgApp.place.priceTo">Price To</Translate>
                  </Label>
                  <AvField id="place-my-suffix-priceTo" type="number" className="form-control" name="priceTo" />
                </AvGroup>
                <AvGroup>
                  <Label id="latitudeLabel" for="latitude">
                    <Translate contentKey="vtgApp.place.latitude">Latitude</Translate>
                  </Label>
                  <AvField id="place-my-suffix-latitude" type="number" className="form-control" name="latitude" />
                </AvGroup>
                <AvGroup>
                  <Label id="longitudeLabel" for="longitude">
                    <Translate contentKey="vtgApp.place.longitude">Longitude</Translate>
                  </Label>
                  <AvField id="place-my-suffix-longitude" type="number" className="form-control" name="longitude" />
                </AvGroup>
                <AvGroup>
                  <Label id="provinceIdLabel" for="provinceId">
                    <Translate contentKey="vtgApp.place.provinceId">Province Id</Translate>
                  </Label>
                  <AvField id="place-my-suffix-provinceId" type="number" className="form-control" name="provinceId" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/place-my-suffix" replace color="info">
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
  placeEntity: storeState.place.entity,
  loading: storeState.place.loading,
  updating: storeState.place.updating
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
)(PlaceMySuffixUpdate);
