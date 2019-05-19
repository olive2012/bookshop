import React, {Component} from 'react';
import {Bar, Doughnut} from "react-chartjs-2";

class Visual1 extends Component {
    state = {
        // pavyzdinis data objektas, nukopijuotas is
        // https://github.com/jerairrest/react-chartjs-2/blob/master/example/src/components/doughnut.js
        // nereikalingas cia, bet patogus kaip reference i tai ka turime sukurti
        // dokumentacija koks turi buti objektas yra cia:
        // https://www.chartjs.org/docs/latest/charts/doughnut.html
        // bet kas skaito dokumentacija kai galima paziureti  example ir daryti taip pat
        chartData: {
            labels: [
                'Red',
                'Green',
                'Yellow'
            ],
            datasets: [{
                data: [300, 50, 100],
                backgroundColor: [
                    '#FF6384',
                    '#36A2EB',
                    '#FFCE56'
                ],
                hoverBackgroundColor: [
                    '#FF6384',
                    '#36A2EB',
                    '#FFCE56'
                ]
            }]
        }
    };

    // Musu standartine palete.
    // paimta kazkieno sukurta is cia: https://gist.github.com/mucar/3898821
    defaultColors = ['#FF6633', '#FFB399', '#FF33FF', '#FFFF99', '#00B3E6',
		  '#E6B333', '#3366E6', '#999966', '#99FF99', '#B34D4D',
		  '#80B300', '#809900', '#E6B3B3', '#6680B3', '#66991A',
		  '#FF99E6', '#CCFF1A', '#FF1A66', '#E6331A', '#33FFCC',
		  '#66994D', '#B366CC', '#4D8000', '#B33300', '#CC80CC',
		  '#66664D', '#991AFF', '#E666FF', '#4DB3FF', '#1AB399',
		  '#E666B3', '#33991A', '#CC9999', '#B3B31A', '#00E680',
		  '#4D8066', '#809980', '#E6FF80', '#1AFF33', '#999933',
		  '#FF3380', '#CCCC00', '#66E64D', '#4D80CC', '#9900B3',
		  '#E64D66', '#4DB380', '#FF4D4D', '#99E6E6', '#6666FF'];

    componentWillReceiveProps(nextProps, nextContext) {
        let newLabels = []; // masyvas su pavadinimais. Tuscias :)
        let newData = []; // masyvais su skaiciais. Tuscias :)

        // paimam po viena knyga ir kiekvienai knygai
        // idedam i newLabels pavadinima
        // idedam i newData puslapius skaiciu

        nextProps.books.map(book => {
            newLabels.push(book.title);
            newData.push(book.numOfPages);
        })

        // sukuriam duomenu objekta toki koki laukia Chart.js
        let newGraphdata = {
            labels: newLabels, datasets: [
                {
                    data: newData,
                    backgroundColor: this.defaultColors.slice(0,newLabels.length)
                    // paimam is paletes tiek spalvu kiek pas mus knygu
                }]
        }

        // na ir galiausiai nustatome busenos objekta
        this.setState({ chartData : newGraphdata });

    }

    chartOptions = {
        cutoutPercentage:10 // kiek ispjauti skylute. 0 - gausim Pie. 50 - tipinis Donought
    }

    render() {
        return (
            <div>

                {/* Piesiam Donata panaudojant busenos objecta "chartData" ir sios klases property "chartOptions"
                perduodant juos kaip props'us .
                Komponentas yra is bibliotekos "react-chartjs-2"
                https://github.com/jerairrest/react-chartjs-2
                */}
                <Doughnut
                    data={this.state.chartData}
                    options={this.chartOptions}/>
            </div>
        );
    }
}

export default Visual1;
