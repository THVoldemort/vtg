import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './hotel-my-suffix.reducer';
import { IHotelMySuffix } from 'app/shared/model/hotel-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHotelMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class HotelMySuffixDetail extends React.Component<IHotelMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { hotelEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vtgApp.hotel.detail.title">Hotel</Translate> [<b>{hotelEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">
                <Translate contentKey="vtgApp.hotel.code">Code</Translate>
              </span>
            </dt>
            <dd>{hotelEntity.code}</dd>
            <dt>
              <span id="name">
                <Translate contentKey="vtgApp.hotel.name">Name</Translate>
              </span>
            </dt>
            <dd>{hotelEntity.name}</dd>
            <dt>
              <span id="numOfRoom">
                <Translate contentKey="vtgApp.hotel.numOfRoom">Num Of Room</Translate>
              </span>
            </dt>
            <dd>{hotelEntity.numOfRoom}</dd>
            <dt>
              <span id="latitde">
                <Translate contentKey="vtgApp.hotel.latitde">Latitde</Translate>
              </span>
            </dt>
            <dd>{hotelEntity.latitde}</dd>
            <dt>
              <span id="longitude">
                <Translate contentKey="vtgApp.hotel.longitude">Longitude</Translate>
              </span>
            </dt>
            <dd>{hotelEntity.longitude}</dd>
          </dl>
          <Button tag={Link} to="/entity/hotel-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/hotel-my-suffix/${hotelEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ hotel }: IRootState) => ({
  hotelEntity: hotel.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HotelMySuffixDetail);
