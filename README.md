# MTG Collection & Pricing Tool

A Java-based tool that processes Magic: The Gathering collection data, retrieves live pricing information, and exports structured reports.

## Overview
This project is designed to automate tracking and valuation of a personal MTG card collection. It focuses on data parsing, external API integration, and exporting clean, usable output for analysis.

## Features
- Processes scanned MTG collection data
- Retrieves card pricing using the Scryfall API
- Exports structured spreadsheets for inventory and valuation
- Emphasis on data integrity and automation

## Tech Stack
- Java
- File I/O
- External API (Scryfall)
- CSV-based data workflows

## Concepts Demonstrated
- File parsing and data transformation
- API consumption
- Error handling and validation
- Automation of repetitive data tasks

## Project Structure
- `api` – External API communication (Scryfall)
- `io` – File input/output and data serialization
- `logic` – Core collection and pricing workflows
- `model` – Data models representing cards and collections
- `ui` – User interaction and program flow
- `MTGApp` – Program entry point

## Status
In Progress (core data workflows implemented; API integration and export features being expanded)

## Planned Improvements
- Complete Scryfall API integration with support for edge cases (alternate printings, missing data)
- Improve error handling and retry logic for external API requests
- Add configuration support for API rate limits and timeouts
- Expand export options (multiple formats, summary views)
- Improve validation and normalization of scanned collection data
-  Add basic logging to track data processing and pricing update steps
